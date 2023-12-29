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

    useEffect(() => {
        const fetchResponse = async () => {
            console.log("ana geet")
            // the two controllers as one with post request
            let response = await getRequestController.sendGetRequest(GET_USER_BACKEND_ENDPOINT);

            // toJSON util to convert ReadableStream to JSON
            let jsonResponse = await toJSON(response.body!);
            let responseStat = response.status;

            setUserDetail({
                firstName: jsonResponse.firstName,
                lastName: jsonResponse.lastName,
                username: jsonResponse.username,
                email: jsonResponse.email,
                phone: jsonResponse.phone,
                password: jsonResponse.password,
                role: jsonResponse.role,
            })
        }
        console.log("goda")
        fetchResponse().then(r => console.log("ana geet2"))
    }, [])

    return (
        <Profile
            userType={"staff"}
            userDetail={userDetail}
            itemList={[{text: "profile"}, {text: "pets"}, {text: "adoption records"}]}
        />
    );
}

export default Page;

