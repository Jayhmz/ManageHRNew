$(document).ready(function () {
    //button
    const takeABreakButton = $('.right');
    const takeABreakButtonText = $('.right span');

    var clickCount = 0;
    function toggleTakeABreakButton(event){

        if(clickCount % 2 == 0){
        event.preventDefault();
         console.log('working inside resume work toggle function...')
                           takeABreakButtonText.text('Resume Work');
                           takeABreakButton.css('background-color', 'green');
                           $.ajax({
                            type: "GET",
                            url: "/app/time-out",
                            contentType: "application/json",
                            success: function(response) {
                                console.log("okay i got here..... "+response)
                            },
                            error: function (error) {
                                console.error("Error", error)
                            }

                           });
        console.log(clickCount+" modulo 2 = 0")
        }else{
        event.preventDefault();
        takeABreakButtonText.text('Go on a break');
         console.log('working inside resume work toggle function...')
                           takeABreakButtonText.text('Take a break');
                           takeABreakButton.css('background-color', '#FD7238');
                           $.ajax({
                            type: "GET",
                            url: "/app/time-in",
                            contentType: "application/json",
                            success: function(response) {
                                console.log("okay i got here too....."+response)
                            },
                            error: function (error) {
                                console.error("Error", error)
                            }

                           });
        console.log(clickCount +" modulo 2 = 1")
        }
        clickCount++;

    console.log(takeABreakButton.text());
    }
    takeABreakButton.on('click', toggleTakeABreakButton);
})