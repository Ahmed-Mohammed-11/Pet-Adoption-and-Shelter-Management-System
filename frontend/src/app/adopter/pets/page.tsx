'use client';
import {GET_USER_BACKEND_ENDPOINT} from "@/app/constants/apiConstants";
import toJSON from "@/app/utils/readableStreamResponseBodytoJSON";
import getRequestController from "@/app/services/getRequestController";
import {useState} from "react";
import Pets from "@/app/components/pets/page";

function Page() {

    const [pets, setPets] = useState([]);

    const fakePets : PetDTO[] = [
        {
            name: "Pet 1",
            species: "Dog",
            breed: "Golden Retreiver",
            age: 2,
            images: [
                "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais",
                "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais",
                "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais",
                "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais",
                "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais",
                "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais",
                "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais",
            ],
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
        let url = GET_USER_BACKEND_ENDPOINT + "?username=adopter1"
        let response = await getRequestController.sendGetRequest(url);

        // toJSON util to convert ReadableStream to JSON
        let jsonResponse = await toJSON(response.body!);
        let responseStat = response.status;

        setPets(jsonResponse)
    }


    return (
        <Pets
            pets={fakePets}
            userType={"adopter"}
            itemList={[{text: "profile"}, {text: "shelters"}, {text: "pets"}]}
        />
    );
}

export default Page;

