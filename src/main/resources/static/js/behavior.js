function deleteOrderConfirmation(delete_location) {
    if(confirm("Are you sure you want to delete this entry?")){
        document.location = delete_location;
    }    
};

function switchDeleteButtons() {
    if($("#itemTextBoxes").children().length > 1){
        $("#itemTextBoxes").find('.btn-danger').show();
    }
    else{
        $("#itemTextBoxes").find('.btn-danger').hide();
    }
};

$(document).ready(switchDeleteButtons());

$(document).ready(function() {
    $("#addItem").click(function() {
        var newItemInputWrapper = $("#itemTextBoxes").children()[0].outerHTML;
        $("#itemTextBoxes").append(newItemInputWrapper);
        $("#itemTextBoxes").find("input").last().val("");
        switchDeleteButtons();
    });

  
});

//Since the class rowInputDeleter is added dynamically the listener has to be at document level
$(document).on('click', '.rowInputDeleter', function(event) {
    event.preventDefault();
    $(event.target).closest(".form-group").remove();
    switchDeleteButtons();    
});