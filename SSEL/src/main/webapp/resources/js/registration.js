jQuery(document).ready(function ($) {
	
	$('#registration_form')
		.on('init.field.bv', function(e, data) {
            var field  = data.field,        
                $field = data.element,      
                bv = data.bv;           
            var $span = $('<small/>')
                	.addClass('help-block validMessage')
                    .attr('data-field', field)
                    .insertAfter($field)
                    .hide();

            var message = $field.attr('data-bv-valid-message');
            if (message) {
                $span.html(message);
            }
		})
		.bootstrapValidator()
		.on('success.field.bv', function(e, data) {
            var field  = data.field,        
                $field = data.element;      
            
            $field.next('.validMessage[data-field="' + field + '"]').show();
        })
        .on('error.field.bv', function(e, data) {
            var field  = data.field,        
                $field = data.element;      

            $field.next('.validMessage[data-field="' + field + '"]').hide();
        });
	
	$('[data-toggle="tooltip"]').tooltip();
	
	
});