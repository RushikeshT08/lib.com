const apiUrl = "http://localhost:3000/transactions"; // Make sure this is your actual API endpoint
let allTransactions = [];
let editMode = false;
let editingId = null;

// Fetch and render transactions
function fetchAndRenderTransactions() {
  fetch(apiUrl)
    .then(res => res.json())
    .then(data => {
      allTransactions = data;
      renderTransactions(data);
    })
    .catch(err => console.error("Fetch error:", err));
}

// Render transactions into the table
function renderTransactions(transactions) {
  const tbody = document.getElementById("transactionTableBody");
  tbody.innerHTML = ""; // Clear table before rendering new data

  transactions.forEach(tx => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${tx.person}</td>
      <td>${tx.bookname}</td>
      <td>${tx.paymentType}</td>
      <td>${tx.amount}</td>
      <td>
        <button class="btn btn-sm btn-outline-primary me-1 edit-btn" data-id="${tx.id}">
          Edit
        </button>
        <button class="btn btn-sm btn-outline-danger delete-btn" data-id="${tx.id}">
          Delete
        </button>
      </td>
    `;
    tbody.appendChild(row);
  });

  // Edit button event listeners
  document.querySelectorAll(".edit-btn").forEach(btn => {
    btn.addEventListener("click", () => {
      const id = btn.getAttribute("data-id");
      loadTransactionForEdit(id);
    });
  });

  // Delete button event listeners
  document.querySelectorAll(".delete-btn").forEach(btn => {
    btn.addEventListener("click", () => {
      const id = btn.getAttribute("data-id");
      deleteTransaction(id);
    });
  });
}


function loadTransactionForEdit(id) {
  const transaction = allTransactions.find(tx => tx.id == id);
  if (transaction) {
    document.getElementById("person").value = transaction.person;
    document.getElementById("bookname").value = transaction.bookname;
    document.getElementById("paymentType").value = transaction.paymentType;
    document.getElementById("amount").value = transaction.amount;

    editMode = true;
    editingId = id;

    document.getElementById("submitBtn").textContent = "Update Transaction";
  }
}

// Delete transaction
function deleteTransaction(id) {
  if (confirm("Are you sure you want to delete this transaction?")) {
    fetch(`${apiUrl}/${id}`, {
      method: "DELETE"
    })
    .then(response => {
      if (response.ok) {
        fetchAndRenderTransactions();
      } else {
        alert("Failed to delete transaction.");
      }
    })
    .catch(err => console.error("Delete error:", err));
  }
}

// Form submit handler (Add or Update)
document.getElementById("transactionForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const person = document.getElementById("person").value;
  const bookname = document.getElementById("bookname").value;
  const paymentType = document.getElementById("paymentType").value;
  const amount = document.getElementById("amount").value;

  const transactionData = { person, bookname, paymentType, amount };

  if (editMode) {
    // Update transaction
    fetch(`${apiUrl}/${editingId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ id: parseInt(editingId), ...transactionData })
    })
    .then(response => {
      if (response.ok) {
        fetchAndRenderTransactions();
        resetForm();
      } else {
        alert("Failed to update transaction.");
      }
    })
    .catch(err => console.error("Update error:", err));
  } else {
    // Add new transaction
    fetch(apiUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        id: Date.now(), // Unique ID using timestamp
        ...transactionData
      })
    })
    .then(response => {
      if (response.ok) {
        fetchAndRenderTransactions();
        resetForm();
      } else {
        alert("Failed to add transaction.");
      }
    })
    .catch(err => console.error("Add error:", err));
  }
});

// Reset form after add/edit
function resetForm() {
  document.getElementById("transactionForm").reset();
  document.getElementById("submitBtn").textContent = "Add Transaction";
  editMode = false;
  editingId = null;
}

// Search transactions by person name
document.getElementById("searchInput").addEventListener("input", function () {
  const searchQuery = this.value.toLowerCase();
  const filteredTransactions = allTransactions.filter(tx =>
    tx.person.toLowerCase().includes(searchQuery)
  );
  renderTransactions(filteredTransactions);
});

// Initial load
fetchAndRenderTransactions();
