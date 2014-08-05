function createTextBoxes(event, txtBoxCount) {
    var iCounter = 0,
        playerText = null,
        scoreText = null;

    for (iCounter = 0; iCounter < txtBoxCount; iCounter++) {
        playerText = document.createElement('input');
        scoreText = document.createElement('input');

        $(playerText).attr('type', 'text');
        $(playerText).attr('class', 'player');
        $(scoreText).attr('type', 'text');
        $(scoreText).attr('class', 'score');

        $(playerText).attr('id', 'player' + (iCounter + 1));
        $(playerText).attr('name', 'player' + (iCounter + 1));
        $(scoreText).attr('id', 'score' + (iCounter + 1));
        $(scoreText).attr('name', 'score' + (iCounter + 1));

        $(playerText).attr('placeholder', 'player ' + (iCounter + 1));
        $(scoreText).attr('placeholder', '0');

        $('#container').append(playerText);
        $('#container').append(scoreText);
    }
}

$("#btnStart").on('click', function(event) {
    createTextBoxes(event, 11);
    $("#btnStart").hide();
    $("#submitButton").show();
});