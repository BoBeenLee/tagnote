function proccessGoogleSuccess(authResult) {
    window.location = "/user/social?accessToken=" + authResult['access_token'] + "&type=google";
}

function proccessGoogleError(authResult){

}

function proccessFacebookSuccess(response) {
    window.location = "/user/social?accessToken=" + response.authResponse.accessToken + "&type=facebook";
}

function proccessFacebookError(response){
    if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.
        document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.
        document.getElementById('status').innerHTML = 'Please log ' +
        'into Facebook.';
    }
   /* document.getElementById('status').innerHTML = 'Please log ' +
    'into this app.';*/
}

