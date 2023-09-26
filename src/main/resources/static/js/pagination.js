// Move the loadStaffData function outside of the document.ready block
//function loadStaffData(link) {
//    var currentPage = $(link).data('page');
//
//    $.ajax({
//        url: '/admin/staffs?page=' + currentPage,
//        method: 'GET',
//        success: function (data) {
//            console.log("paginated data " + data);
//            $('#staff-list-container').html(data);
//        },
//        error: function (xhr, status, error) {
//            console.error('AJAX Error:');
//               console.error('Status: ' + status);
//               console.error('Error: ' + error);
//               console.error('Response Text: ' + xhr.responseText);
//        },
//    });
//}

// $(document).ready(function () {
//     // Handle pagination link clicks
    
// });
// $('.pagination a').on('click', function () {
//   e.preventDefault();
//   $('.side-menu li').removeClass('active')
//   $('#staff').addClass('active');
//   console.log('working here....')
// });