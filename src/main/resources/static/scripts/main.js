$(document).ready(function(){
    $("p").click(function(){
        makeAlert();
        $(this).hide();
    });

    $(".greetings").click(function(){
        $(".greetings")
            .animate({opacity: "0.1", left: "+=400"}, 1200)
            .animate({opacity: "1", right: "+=400"}, 1200)
            .animate({top: "0"}, "fast")
            .slideUp()
        return false;
    });
});

const map = new ol.Map({
    target: 'map',
    layers: [
        new ol.layer.Tile({
            source: new ol.source.OSM()
        })
    ],
    view: new ol.View({
        center: ol.proj.fromLonLat([37.41, 8.82]),
        zoom: 4
    })
});

function makeAlert() {
    alert("HELLO BRO")
}