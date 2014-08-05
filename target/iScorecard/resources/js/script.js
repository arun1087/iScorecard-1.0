/**
 * Created by Arun on 7/13/14.
 */

var MYLIBRARY = MYLIBRARY || (function(){
    var _args = {}; // private

    return {
        init : function(Args) {
            _args = Args;
            // some other initialising
        },
        helloWorld : function() {
            something  = _args[0];
            var scorecard = $('<table/>'),
                numOfTeams = null,
                iCounter1 = 0,
                iCounter2 = 0,
                pCount = 0,
                statCount = 0,
                rowArr = [],
                cellsDataArr = [],
                teamData = {};
            var url = "http://localhost:8080/iScorecard/scorecard?match_id="+_args[0];
            $.get(url, function(data, status) {
                teamData = data;
                console.log(data);
                console.log(teamData);
                numOfTeams = data.scorecard.length;
                for (iCounter1 = 0; iCounter1 < numOfTeams; iCounter1++) {
                    for(pCount = 0; pCount<11; pCount++) {
                        rowArr.push($('<tr/>'));
                        for(statCount = 0 ; statCount<2; statCount++) {
                            cellsDataArr.push($('<td/>').text(teamData.scorecard[iCounter1].player1));
                            cellsDataArr.push($('<td/>').text(teamData.scorecard[iCounter1].score1));
                        }
                    }
                    for (iCounter2 = 0; iCounter2 < 3; iCounter2++) {
                        rowArr[iCounter1].addClass('record-css-class').append(cellsDataArr[iCounter2]);
                        scorecard.append(rowArr[iCounter1]);
                    }
                    cellsDataArr = [];
                }
                $('body').append(scorecard);
            });
        }
    };
}());


function renderHTML() {


}

renderHTML();
