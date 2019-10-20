function deleteOrderConfirmation(delete_location) {
    if(confirm("Are you sure you what to delete this entry?")){
        document.location = delete_location;
    }    
}
