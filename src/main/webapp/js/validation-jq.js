var rg_name = /^[A-Za-z]{3,20}$/;
var rg_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
var rg_password = /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
var rg_captcha = /^([a-zA-Z0-9]){6}/;

function validateLastName() {
    if (!rg_name.test($("#lastName").val())) {
       document.getElementById("lNameErr").innerHTML = "Invalid last name, should contain only latin at least 3 characters";
        return false;
    } else {
        $("#lNameErr").html("");
    return true;
    }
}

function validateFirstName() {
    if (!rg_name.test($("#firstName").val())) {
        document.getElementById("fNameErr").innerHTML = "Invalid first name, should contain only latin at least 3 characters";
        return false;
    } else {
        $("#fNameErr").html("");
        return true;
    }
}

function validatePwd() {
     if (!rg_password.test($("#password").val())) {
            document.getElementById("pwdErr").innerHTML = "Invalid password, should contain latin, digit at least 6 and max 20 characters";
             return false;
    } else {
        $("#pwdErr").html("");
        return true;
    }
}

function validateConfirmPwd() {
     if ($("#password").val() != $("#confirmPassword").val()) {
            document.getElementById("confirmErr").innerHTML = "Passwords does not match";
             return false;
    } else {
        $("#confirmErr").html("");
        return true;
    }
}

function validateEmail() {
        if (!rg_email.test($("#email").val())) {
            document.getElementById("emailErr").innerHTML = "Invalid email. Valid e-mail should contain only latin letters, numbers, '@' and '.'";
            return false;
    } else {
        {
            $("#emailErr").html("");
            return true;
        }
    }
}

function validateCaptcha() {
    if (!rg_captcha.test($("#guess-captcha").val())) {
            document.getElementById("captchaErr").innerHTML = "Invalid captcha. Valid captcha should contain 6 symbols only latin letters and numbers";
            return false;
    } else {
        {
            $("#captchaErr").html("");
            return true;
        }
    }
    var value = document.forms["register-form"]["guess-captcha"].value
    return (value.test(regularCaptcha));
}

function validateForm() {
    if (validatePwd() & validateLastName() & validateFirstName() & validateEmail() & validateConfirmPwd() & validateCaptcha()) {
        return true;
     }
     return false;
}

$("#firstName").blur(validateFirstName);
$("#lastName").blur(validateLastName);
$("#email").blur(validateEmail);
$("#password").blur(validatePwd);
$("#confirmPassword").blur(validateConfirmPwd);
$("#captcha").blur(validateCaptcha);


