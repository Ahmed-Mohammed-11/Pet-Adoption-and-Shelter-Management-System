'use client';

import Profile from "@/app/components/profile/page";

function Page() {

    //this should come from backend request
    const userDetail = {
        fullName: 'Ahmed Mohamed',
        username: 'manager1',
        email: 'manager1@petcorp.com',
        phone: '+20444555666',
        password: '111 222 333',
        role: 'Manager',
    }

    return (
        <Profile
            userDetail={userDetail}
            userType={"manager"}
            itemList={[{text: "profile"}, {text: "shelters"}, {text: "pets"}]}
        />
    );
}

export default Page;

