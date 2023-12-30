'use client';
import React from "react";
import Pets from "@/app/components/pets/page";
import PetFilter from "@/app/components/filter/page";

function Page() {
    return (
        <>
            <Pets
                userType={"staff"}
                itemList={[{text: "profile"}, {text: "pets"}, {text: "adoption records"}]}
            />
        </>
    );
}

export default Page;

