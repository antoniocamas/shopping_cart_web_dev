function deleteOrderConfirmation(delete_location) {
    if(confirm("Are you sure you want to delete this entry?")){
        document.location = delete_location;
    }    
};

var newItemInputWrapper = "";

$(document).ready(function() {
    $("#addItem").click(function() {
        if(!newItemInputWrapper){
            newItemInputWrapper = $("#itemTextBoxes").first().html();
        }        
        $("#itemTextBoxes").append(newItemInputWrapper);
        $("#itemTextBoxes").find('.btn-danger').show();
    });

  
});

//Since the class rowInputDeleter is added dynamically the listener has to be at document level
$(document).on('click', '.rowInputDeleter', function(event) {
    event.preventDefault();
    $(event.target).closest(".form-group").remove();
    
});