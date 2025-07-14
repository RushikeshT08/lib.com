const apiUrl = "http://localhost:3000/users"; 


document.getElementById("registrationForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const username = document.getElementById("username").value.trim();
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();

  if (!username || !email || !password) {
    alert("Please fill in all fields.");
    return;
  }

  const newUser = {
    id: Date.now(), 
    username,
    email,
    password
  };

  fetch(apiUrl, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(newUser)
  })
  .then(res => {
    if (res.ok) {
      alert("User registered successfully!");
      document.getElementById("registrationForm").reset();
    } else {
      alert("Failed to register user.");
    }
  })
  .catch(err => console.error("Add user error:", err));
});
