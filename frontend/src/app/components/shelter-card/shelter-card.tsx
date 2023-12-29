'use client';
import {Card, CardActionArea, CardContent, CardMedia, Divider, Typography} from '@mui/material';
import styles from './page.module.css'
import BusinessIcon from '@mui/icons-material/Business';
import AlternateEmailIcon from '@mui/icons-material/AlternateEmail';
import LocalPhoneIcon from '@mui/icons-material/LocalPhone';

const DEFAULT_IMAGES = [
    "https://media.istockphoto.com/id/1314520580/vector/homeless-animals-people-in-shelter-with-pet-cats-and-dogs-in-cages-vector-concept.jpg?s=612x612&w=0&k=20&c=5wEsYW_lNNnS6vJj5m6ebNljzzAdcZM6zEG9PEfE92A=",
    "https://img.freepik.com/free-vector/happy-volunteers-with-badges-working-animal-shelter-taking-care-about-homeless-cats-dogs-cages-vector-illustration-adopting-pet-animal-care-concept_74855-13124.jpg",
    "https://static.vecteezy.com/system/resources/previews/009/951/691/original/animal-shelter-cartoon-illustration-with-pets-sitting-in-cages-and-volunteers-feeding-animals-for-adopting-in-flat-hand-drawn-style-design-vector.jpg",
    "https://img.freepik.com/free-vector/animal-shelter-horizontal-illustration-with-pets-sitting-cages-volunteers-feeding-animals-flat_1284-32955.jpg",
    "https://thumbs.dreamstime.com/b/family-adopting-pet-animal-shelter-flat-cartoon-vector-illustration-banner-pets-shelter-interior-volunteers-animals-196377441.jpg",
]

interface Props {
    shelter: ShelterDTO,
}

function ShelterCard(props: Props) {

    const shelter = props.shelter;
    const imgIndex = Math.floor(Math.random() * 5);

    return (
        <>
            <Card className={styles.card}>
                <CardActionArea>
                    <CardMedia
                        component="img"
                        alt="default image"
                        height="140"
                        image={DEFAULT_IMAGES[imgIndex]}
                    />
                    <CardContent>
                        <Typography className={styles.card_header} gutterBottom fontSize={24}>
                            {shelter.name}
                        </Typography>
                        <Typography color="gray" fontSize={16}>
                            <BusinessIcon fontSize='inherit'/>
                            &nbsp;{shelter.location}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}}/>
                        <Typography color="gray" fontSize={16}>
                            <LocalPhoneIcon fontSize='inherit'/>
                            &nbsp;{shelter.phone}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}}/>
                        <Typography color="gray" fontSize={16}>
                            <AlternateEmailIcon fontSize='inherit'/>
                            &nbsp;{shelter.email}
                        </Typography>
                    </CardContent>
                </CardActionArea>
            </Card>
        </>
    );
}

export default ShelterCard;

