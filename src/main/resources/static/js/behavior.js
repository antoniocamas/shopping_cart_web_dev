function deleteOrderConfirmation(delete_location) {
    if(confirm("Are you sure you want to delete this entry?")){
        document.location = delete_location;
    }    
}

$(document).ready(function() {
    $("#addItem").click(function() {
        
        
        //var lastField = $("#itemTextBoxes");
        //var intId = (lastField && lastField.length && lastField.data("idx") + 1);
        //var newItemInputWrapper = $("<div class=\"form-group\"><input type=\"text\" class=\"form-control-user\" placeholder=\"Item Name\" name=\"items\" id=\"inputItem" + intId + "\"></div>");
        var newItemInputWrapper = $("<div class=\"form-group\"><input type=\"text\" class=\"form-control-user\" placeholder=\"Item Name\" name=\"items\"></div>");
        console.log(newItemInputWrapper);
        //newItemInputWrapper.data("idx", intId);
        console.log($("#itemTextBoxes").html());
        $("#itemTextBoxes").append(newItemInputWrapper);
        console.log($("#itemTextBoxes").html());
    });
});
