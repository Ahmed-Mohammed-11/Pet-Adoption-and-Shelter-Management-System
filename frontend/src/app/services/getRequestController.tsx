import {BASE_BACKEND_URL} from "@/app/constants/apiConstants";

class GetRequestController implements IGetRequestController {
    sendGetRequest(endpoint: string) {
        const url = BASE_BACKEND_URL + endpoint;
        return fetch(url, {
            method: "GET"
        });
    }
}

const getRequestController = new GetRequestController();
export default getRequestController;