/**
 * Created by BoBinLee on 2015-05-13.
 */

// This is called with the results from from googleSigninButton
function signinGoogleCallback(authResult) {
    if (authResult['access_token']) {
        proccessGoogleSuccess(authResult);
    } else if (authResult['error']) {
        // 가능한 오류 코드:
        //   "access_denied" - 사용자가 앱에 대한 액세스 거부
        //   "immediate_failed" - 사용자가 자동으로 로그인할 수 없음
        // console.log('오류 발생: ' + authResult['error']);
        proccessGoogleError(authResult);
    }
}

// Load the Google asynchronously
(function () {
    var po = document.createElement('script');
    po.type = 'text/javascript';
    po.async = true;
    po.src = 'https://apis.google.com/js/client:plusone.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(po, s);
})();

