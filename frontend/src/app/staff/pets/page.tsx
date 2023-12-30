'use client';
import {GET_USER_BACKEND_ENDPOINT, SIGN_IN_BACKEND_ENDPOINT} from "@/app/constants/apiConstants";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import getRequestController from "@/app/services/getRequestController";
import {useState} from "react";
import Pets from "@/app/components/pets/page";

function Page() {

    const [shelters, setShelters] = useState([]);

    const fakePets : PetDTO[] = [
        {
            name: "Pet 1",
            species: "Dog",
            breed: "Golden Retreiver",
            age: 2,
        },
        {
            name: "Pet 2",
            species: "Cat",
            breed: "Brithish short hair",
            age: 3,
        },
        {
            name: "Pet 3",
            species: "Hamster",
            breed: "Orange",
            age: 1,
        },
        {
            name: "Pet 4",
            species: "Otter",
            breed: "M3sh 3aref",
            age: 5,
        },
        {
            name: "Pet 5",
            species: "Zeby",
            breed: "Manga",
            age: 4,
        },
    ]

    const fetchResponse = async () => {

        // the two controllers as one with post request
        let url = GET_USER_BACKEND_ENDPOINT + "?username=staff1"
        let response = await getRequestController.sendGetRequest(url);

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;

        setShelters(jsonResponse)
    }


    return (
        <Pets
            // pets={fakePets}
            userType={"staff"}
            itemList={[{text: "profile"}, {text: "pets"}, {text: "adoption records"}]}
        />
    );
}

export default Page;

