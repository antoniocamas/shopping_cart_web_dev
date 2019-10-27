function deleteOrderConfirmation(delete_location) {
    if (confirm("Are you sure you want to delete this entry?")) {
        document.location = delete_location;
    }
};

function switchDeleteButtons() {
    if ($("#itemTextBoxes").children().length > 1) {
        $("#itemTextBoxes").find('.btn-danger').show();
    }
    else {
        $("#itemTextBoxes").find('.btn-danger').hide();
    }
};

$(document).ready(
    switchDeleteButtons(),
    switchForm(),
    checkFormSetValues()
    );

$(document).ready(function () {
    $("#addItem").click(function () {
        var newItemInputWrapper = $("#itemTextBoxes").children()[0].outerHTML;
        $("#itemTextBoxes").append(newItemInputWrapper);
        $("#itemTextBoxes").find("input").last().val("");
        switchDeleteButtons();
        switchForm();
    });
});

//Since the class rowInputDeleter is added dynamically the listener has to be at document level
$(document).on('click', '.rowInputDeleter', function (event) {
    event.preventDefault();
    $(event.target).closest(".form-group").remove();
    switchDeleteButtons();
    switchForm();
});

$(document).on('change','form :input', function (event) {
    switchForm();
});


function switchForm(){
    if(validateForm()){
        $("#btnSubmit").attr("disabled", false);
    }
    else{
        $("#btnSubmit").attr("disabled", true);
    }
};

function validateForm() {
    var empty = false;
    $('form :input[type=text]').each(function (index, data) {
        if ($(data).val() == "") {
            empty = true;
            return false;
        }
        
    });
    if (empty){
        return false;
    }
    return true;
};

$("#ItemsCheckBoxes").click(function (event) {

    checkFormUpdateValue(event.target);

});

function checkFormSetValues(){

    $("input[type='checkbox']").each(function (index, data){
        checkFormUpdateValue(data);
    })
};

function checkFormUpdateValue(target){
    if($(target).is(':checked')){
        $(target).val('true');
        $("label[for='"+$(target).attr("id")+"']").html($("label[for='"+$(target).attr("id")+"']").text().strike());
    }
    else{
        $(target).val('false');
        $("label[for='"+$(target).attr("id")+"']").html($("label[for='"+$(target).attr("id")+"']").text());
    }
};
