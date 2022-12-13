const ajaxUrl = "ajax/meals/";

const ctx = {
    ajaxUrl: ajaxUrl,
};

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": true,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ]
        })
    );
});


