$(document).ready(function() {
  // Attach a click event handler to a button or element that triggers the AJAX request
  $('#staff').click(function() {
      // Make an AJAX request
      $.ajax({
          url: '/admin/staffs', // Replace with the URL of the server endpoint
          type: 'GET', // Use GET or POST depending on your needs
          dataType: 'html', // Expect HTML response
          success: function(data) {
             var extractedContent = $(data).find("#staff-content").html();
//             console.log(extractedContent);
              // Update the content with the response
              $('#layout').html(extractedContent);
              // alert(' someting is coming');
//               $('#staff').addClass('active');

          },
          error: function(xhr, status, error) {
              // Handle errors here
              console.error(error);
          }
      });
  });
});

$('#view-staff').click( function(e) {
    e.preventDefault();
    $('.side-menu li').removeClass('active')
    $('#staff').click();
    $('#staff').addClass('active');
})
