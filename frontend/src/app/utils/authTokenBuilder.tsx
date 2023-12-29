/*
if we used jwt or any other authentication method we will delete this file
 */
function buildAuthToken(token: string) {
    return "Bearer " + token;
}

export default buildAuthToken;