'use client';
import { Card, CardActionArea, CardContent, CardMedia, Divider, Typography } from '@mui/material';
import styles from './page.module.css'
import BusinessIcon from '@mui/icons-material/Business';
import AlternateEmailIcon from '@mui/icons-material/AlternateEmail';
import LocalPhoneIcon from '@mui/icons-material/LocalPhone';

const DEFAULT_IMAGE = "https://media.istockphoto.com/id/1314520580/vector/homeless-animals-people-in-shelter-with-pet-cats-and-dogs-in-cages-vector-concept.jpg?s=612x612&w=0&k=20&c=5wEsYW_lNNnS6vJj5m6ebNljzzAdcZM6zEG9PEfE92A="

interface Props {
    shelter : ShelterDTO,
}

function ShelterCard (props : Props) {

    const shelter = props.shelter;

    return (
        <>
            <Card className={styles.card}>
                <CardActionArea>
                    <CardMedia 
                        component="img"
                        alt="default image"
                        height="140"
                        image = {DEFAULT_IMAGE}
                    />
                    <CardContent>
                        <Typography gutterBottom fontSize={24}>
                            {shelter.name}
                        </Typography>
                        <Typography color="gray" fontSize={16}>
                            <BusinessIcon fontSize='inherit' />
                            &nbsp;{shelter.location}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}} />
                        <Typography color="gray" fontSize={16}>
                            <LocalPhoneIcon fontSize='inherit' />
                            &nbsp;{shelter.phone}
                        </Typography>
                        <Divider sx={{margin: '0.5vh 0.5vw'}} />
                        <Typography color="gray" fontSize={16}>
                            <AlternateEmailIcon fontSize='inherit' />
                            &nbsp;{shelter.email}
                        </Typography>
                    </CardContent>
                </CardActionArea>
            </Card>
        </>
    );
}

export default ShelterCard;