$(document).ready(function () {
  //STAFF
  $("#staff").click(function () {
    $.ajax({
      url: "/admin/staffs", // Replace with the URL of the server endpoint
      type: "GET", // Use GET or POST depending on your needs
      dataType: "html", // Expect HTML response
      success: function (data) {
        var extractedContent = $(data).find("#staff-content").html();
        $("#layout").html(extractedContent);
      },
      error: function (xhr, status, error) {
        // Handle errors here
        console.error(error);
      },
    });
  });

  //LEAVE REQUEST
  $("#leave-request").click(function () {
    // Make an AJAX request
    $.ajax({
      url: "/admin/leave-request", // Replace with the URL of the server endpoint
      type: "GET", // Use GET or POST depending on your needs
      dataType: "html", // Expect HTML response
      success: function (data) {
        var extractedContent = $(data).find("#leave-request-content").html();
        $("#layout").html(extractedContent);
      },
      error: function (xhr, status, error) {
        // Handle errors here
        console.error(error);
      },
    });
  });
});

$("#view-staff").click(function (e) {
  e.preventDefault();
//  $(".side-menu li").removeClass("active");
//  $("#staff").click();
//  $("#staff").addClass("active");
  console.log("now working in view staff page");
});




