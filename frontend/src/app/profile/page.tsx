'use client';
import ShelterCard from '../components/ShelterCard';

function Page () {

    let fakeShelter : ShelterDTO = {
        name: "Shelter Name",
        location: "10 Ahmed Ali st., Alexandria",
        phone: "+201234567891",
        email: "shelter123@example.com",
    }

    return (
        <>
        <ShelterCard shelter={fakeShelter}/>
        </>
    );
}

export default Page;