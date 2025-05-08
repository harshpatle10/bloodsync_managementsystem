function validationform(){
   let data1= document.getElementById("email").value.trim();
   let data2= document.getElementById("pass").value.trim();
  if(data1==""){
    alert("please enter email ");
    return false;
  }
  if(data2==""){
    alert("please eneter password");
    return false;
  }
  return true;

}