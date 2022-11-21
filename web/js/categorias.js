$("#lancamento-dataTable").DataTable({
    "columns": [
        {
            "className": 'btn btn-success',
            "orderable": false,
            "data": "",
            "defaultContent": ""
        },
        { "data": "classe" },
        { "data": "conta" },
        { "data": "categoria" },
        { "data": "valor" },            
        { "data": "operacao" },            
        { "data": "data" },            
        { "data": "descricao" },            
        {
            "className": 'btn btn-danger',
            "orderable": false,
            "data": "",
            "defaultContent": ""
        }
    ],
    "columnDefs": [ {
        "data": "id",
        "render": function ( data, type, row, meta ) {
          return '<a href="'+data+'">Editar</a>';
        }
      } ]
});