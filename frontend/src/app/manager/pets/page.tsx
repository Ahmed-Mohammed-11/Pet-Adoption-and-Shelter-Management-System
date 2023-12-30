'use client';
import Pets from "@/app/components/pets/page";
import PetFilter from "@/app/components/filter/page";

function Page() {
    return (
        <>
            <Pets
                userType={"manager"}
                itemList={[{text: "profile"}, {text: "shelters"}, {text: "pets"}]}
            />
        </>

    );
}

export default Page;

