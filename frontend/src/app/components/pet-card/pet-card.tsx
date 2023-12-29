'use client';
import {Card, CardActionArea, CardContent, CardMedia, Divider, Typography} from '@mui/material';
import styles from './page.module.css'
import PetsIcon from '@mui/icons-material/Pets';
import ChildFriendlyIcon from '@mui/icons-material/ChildFriendly';
import FingerprintIcon from '@mui/icons-material/Fingerprint';

const DEFAULT_IMAGE = "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg?size=626&ext=jpg&ga=GA1.1.1546980028.1702857600&semt=ais"

interface Props {
    pet: PetDTO,
}

function PetCard(props: Props) {

    const pet = props.pet;

    return (
        <>
            <Card className={styles.card}>
                <CardActionArea>
                    <CardMedia
                        component="img"
                        alt="default image"
                        height="140"
                        image={DEFAULT_IMAGE}
                    />
                    <CardContent>
                        <Typography className={styles.card_header} gutterBottom fontSize={24}>
                            {pet.name}
                        </Typography>
                        <Typography color="gray" fontSize={16}>
                            <PetsIcon fontSize='inherit'/>
                            &nbsp;{pet.species}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}}/>
                        <Typography color="gray" fontSize={16}>
                            <FingerprintIcon fontSize='inherit'/>
                            &nbsp;{pet.breed}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}}/>
                        <Typography color="gray" fontSize={16}>
                            <ChildFriendlyIcon fontSize='inherit'/>
                            &nbsp;{pet.age}
                        </Typography>
                    </CardContent>
                </CardActionArea>
            </Card>
        </>
    );
}

export default PetCard;

