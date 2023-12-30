'use client';
import Pets from "@/app/components/pets/page";

function Page() {

    return (
        <Pets
            userType={"adopter"}
            itemList={[{text: "profile"}, {text: "pets"}, {text: "applyForAdoption"}]}
        />
    );
}

export default Page;

