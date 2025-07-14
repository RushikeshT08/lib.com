const apiUrl = "http://localhost:3000/categories";
let allCategories = [];
let editMode = false;
let editingId = null;
let sortDirection = 1;

// Create and insert search input
const searchInput = document.createElement("input");
searchInput.setAttribute("type", "text");
searchInput.setAttribute("placeholder", "Search by Author Name...");
searchInput.classList.add("form-control", "mb-3");

const table = document.querySelector(".table");
table.parentNode.insertBefore(searchInput, table);

// Fetch and render categories
function fetchAndRenderCategories() {
    fetch(apiUrl)
        .then(res => res.json())
        .then(data => {
            allCategories = data;
            renderCategories(data);
        })
        .catch(err => console.error("Fetch error:", err));
}

// Render categories into the table
function renderCategories(categories) {
    const tbody = document.getElementById("categoryTableBody");
    tbody.innerHTML = "";

    categories.forEach(cat => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${cat.AuthorId}</td>
            <td>${cat.Name}</td>
            <td>${cat.Bio}</td>
            <td>
                <button class="btn btn-sm btn-outline-primary me-1 edit-btn" data-id="${cat.id}">
                    <i class="bi bi-pencil-square"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger delete-btn" data-id="${cat.id}">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        `;
        tbody.appendChild(row);
    });

   
    document.querySelectorAll(".edit-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const id = btn.getAttribute("data-id");
            loadCategoryForEdit(id);
        });
    });

    
    document.querySelectorAll(".delete-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const id = btn.getAttribute("data-id");
            deleteCategory(id);
        });
    });
}


function loadCategoryForEdit(id) {
    const category = allCategories.find(cat => cat.id == id);
    if (category) {
        document.getElementById("authorid").value = category.AuthorId;
        document.getElementById("name").value = category.Name;
        document.getElementById("bio").value = category.Bio;

        editMode = true;
        editingId = id;
        document.getElementById("submitBtn").textContent = "Update Category";
    }
}

// Delete category
function deleteCategory(id) {
    if (confirm("Are you sure you want to delete this category?")) {
        fetch(`${apiUrl}/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                fetchAndRenderCategories();
            } else {
                alert("Failed to delete category.");
            }
        })
        .catch(err => console.error("Delete error:", err));
    }
}

// Form submit for Add or Update
document.getElementById("categoryForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const AuthorId = document.getElementById("authorid").value;
    const Name = document.getElementById("name").value;
    const Bio = document.getElementById("bio").value;

    const categoryData = { AuthorId, Name, Bio };

    if (editMode) {
        fetch(`${apiUrl}/${editingId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ id: parseInt(editingId), ...categoryData })
        })
        .then(response => {
            if (response.ok) {
                fetchAndRenderCategories();
                resetForm();
            } else {
                alert("Failed to update category.");
            }
        })
        .catch(err => console.error("Update error:", err));
    } else {
        fetch(apiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                id: Date.now(),
                ...categoryData
            })
        })
        .then(response => {
            if (response.ok) {
                fetchAndRenderCategories();
                resetForm();
            } else {
                alert("Failed to add category.");
            }
        })
        .catch(err => console.error("Add error:", err));
    }
});


// Search filter
searchInput.addEventListener("input", () => {
    const query = searchInput.value.toLowerCase();
    const filtered = allCategories.filter(cat =>
        cat.Name.toLowerCase().includes(query)
    );
    renderCategories(filtered);
});

// Initial load
fetchAndRenderCategories();
