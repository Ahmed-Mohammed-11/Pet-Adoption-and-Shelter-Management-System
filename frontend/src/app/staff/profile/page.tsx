'use client';

import Profile from "@/app/components/profile/page";
import {GET_USER_BACKEND_ENDPOINT} from "@/app/constants/apiConstants";
import getRequestController from "@/app/services/getRequestController";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import {useEffect, useState} from "react";

function Page() {

    const [userDetail, setUserDetail] = useState({
        firstName: "",
        lastName: "",
        username: "",
        email: "",
        phone: "",
        password: "",
        role: "",
    });

    // const [userDetail, setUserDetail] = useState( () => fetchResponse())

    const [firstRender, setFirstRender] = useState(true);

    useEffect(() => {
        if(!firstRender) return;
        const fetchResponse = async () => {
            // the two controllers as one with post request
            let response = await getRequestController.sendGetRequest(GET_USER_BACKEND_ENDPOINT);

            // toJSON util to convert ReadableStream to JSON
            let jsonResponse = await toJSON(response.body!);
            let responseStat = response.status;

            setUserDetail({
                ...jsonResponse
            })
        }

        fetchResponse();
        setFirstRender(false);

    }, [firstRender])

    return (
        <Profile
            userType={"staff"}
            userDetail={userDetail}
            itemList={[{text: "profile"}, {text: "pets"}, {text: "adoptionRecords"}]}
        />
    );
}

export default Page;

