function uploadAction() {
     var formData = new FormData(document.getElementById("upload_form"));

     $.ajax({
        type: 'POST',
        url: "/v1/file/upload",
        data: formData,
        processData:false,
        contentType:false,
        success: function (data) {
            console.log(data);
            var error = document.getElementById("error");
            error.textContent="Upload Successfully, file id=" + data;
            error.hidden=false;
        },
        error: function(data) {
            console.log(data);
            var error = document.getElementById("error");
            error.textContent="Upload failed";
            error.hidden=false;
        },
        async: true
    });
}

function deleteAction() {
     var fileID = document.getElementById("fileID").value;
     $.ajax({
        type: 'DELETE',
        url: "/v1/file/delete?" + $.param({ id: fileID }),
        data: {},
        success: function (data) {
            console.log(data);
            var error = document.getElementById("error");
            error.textContent="Delete successfully";
            error.hidden=false;
        },
        error: function(data) {
            console.log(data);
            var error = document.getElementById("error");
            error.textContent="Delete failed";
            error.hidden=false;
        },
        async: true
    });
}