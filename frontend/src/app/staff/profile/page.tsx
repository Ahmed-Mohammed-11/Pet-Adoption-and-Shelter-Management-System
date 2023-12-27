'use client';

import Profile from "@/app/components/profile/page";

function Page() {

    //this should come from backend request
    const userDetail = {
        fullName: 'Ahmed Mohamed',
        username: 'staff1',
        email: 'staff1@petcorp.com',
        phone: '+20111222333',
        password: '111 222 333',
        role: 'Staff',
    }

    return (
        <Profile
            userType={"staff"}
            userDetail={userDetail}
            itemList={[{text: "profile"}, {text: "pets"}, {text: "adoption records"}]}
        />
    );
}

export default Page;

