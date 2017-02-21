
$(document).ready(function() {
    $(function() {
        $("#name").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "./getSuggestion",
                    type: "POST",
                    data: { term: request.term },

                    dataType: "json",

                    success : function(data) {
						response(data);
					}
               });              
            }    
        });
    });
});