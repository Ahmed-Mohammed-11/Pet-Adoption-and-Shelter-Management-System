import {BASE_BACKEND_URL} from "@/app/constants/apiConstants";

class GetRequestController implements IGetRequestController {
    sendGetRequest(endpoint: string) {
        const url = BASE_BACKEND_URL + endpoint;
        const headers = new Headers();

        headers.append('Content-Type', 'application/json');
        headers.append('mode', 'cors')
        headers.append('Authorization', localStorage.getItem("Authorization")!);
        // headers.append("useCredentials", "true");
        console.log(localStorage.getItem("Authorization")!);
        return fetch(url, {
            method: "GET",
            headers: headers
        });
    }
}

const getRequestController = new GetRequestController();
export default getRequestController;