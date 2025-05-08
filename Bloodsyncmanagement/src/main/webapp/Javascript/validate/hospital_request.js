function validateForm() {
  let hospitalName = document.getElementById("hostpital_name").value.trim();
  let bloodUnits = document.getElementById("blood_units").value.trim();

  if (hospitalName === "") {
    alert("Please enter hospital name");
    return false;
  }
  bloodUnits = Number(bloodUnits);
  if (bloodUnits <= 0) {
    alert("Please enter a valid blood unit");
    return false;
  }
  return true;
}
