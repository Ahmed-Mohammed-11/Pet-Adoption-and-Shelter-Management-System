'use client';
import {GET_USER_BACKEND_ENDPOINT, SIGN_IN_BACKEND_ENDPOINT} from "@/app/constants/apiConstants";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import getRequestController from "@/app/services/getRequestController";
import {useState} from "react";
import Applications from "@/app/components/Applications/page";
import ApplicationDTO from "@/app/entities/dtos/ApplicationDTO";

function Page() {

    const fetchResponse = async () => {

        // the two controllers as one with post request
        let url = GET_USER_BACKEND_ENDPOINT + "?username=staff1"
        let response = await getRequestController.sendGetRequest(url);

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;

    }


    return (
        <>
        {/* Here we need to get the shelter ID to get the Application records */}
        <Applications
            shelterId={1}
            userType={"staff"}
            itemList={[{text: "profile"}, {text: "pets"}, {text: "adoptionRecords"}]}
        />
        </>
    );
}

export default Page;

