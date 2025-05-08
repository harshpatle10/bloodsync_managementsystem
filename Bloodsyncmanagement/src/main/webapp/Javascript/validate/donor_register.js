function validateForm() {
  let name = document.getElementById("name").value.trim();
  let city = document.getElementById("city").value.trim();
  let contact = document.getElementById("contact").value.trim();
  if (name === "") {
    alert("Please enter full name");
    return false;
  }
  if (city === "") {
    alert("Please enter city name");
    return false;
  }
  if (contact.length != 10) {
    alert("Please enter a valid contact number");
    return false;
  }
  return true;
}
