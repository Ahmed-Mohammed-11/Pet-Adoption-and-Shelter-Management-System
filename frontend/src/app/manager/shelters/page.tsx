'use client';
import {GET_USER_BACKEND_ENDPOINT, SIGN_IN_BACKEND_ENDPOINT} from "@/app/constants/apiConstants";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import getRequestController from "@/app/services/getRequestController";
import {useState} from "react";
import Shelters from "@/app/components/shelters/page";

function Page() {

    const [shelters, setShelters] = useState([]);

    const fakeShelters : ShelterDTO[] = [
        {
            name: "Shelter 1",
            location: "69 Ahmed Ali st.,",
            phone: "+201234567891",
            email: "shelter@example.com"
        },
        {
            name: "Shelter 2",
            location: "69 Ahmed Ali st.,",
            phone: "+201234567891",
            email: "shelter@example.com"
        },
        {
            name: "Shelter 3",
            location: "69 Ahmed Ali st.,",
            phone: "+201234567891",
            email: "shelter@example.com"
        },
        {
            name: "Shelter 4",
            location: "69 Ahmed Ali st.,",
            phone: "+201234567891",
            email: "shelter@example.com"
        },
        {
            name: "Shelter 5",
            location: "69 Ahmed Ali st.,",
            phone: "+201234567891",
            email: "shelter@example.com"
        },
    ]

    const fetchResponse = async () => {

        // the two controllers as one with post request
        let url = GET_USER_BACKEND_ENDPOINT + "?username=manager1"
        let response = await getRequestController.sendGetRequest(url);

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;

        setShelters(jsonResponse)
    }


    return (
        <Shelters
            shelters={fakeShelters}
            userType={"manager"}
            itemList={[{text: "profile"}, {text: "shelters"}, {text: "pets"}]}
        />
    );
}

export default Page;

