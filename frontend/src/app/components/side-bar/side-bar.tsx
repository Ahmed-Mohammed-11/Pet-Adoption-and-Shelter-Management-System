'use client';
import {Avatar, Link} from "@mui/material";
import {Box} from "@mui/system";
import styles from './page.module.css'
import {deepOrange} from "@mui/material/colors";

interface Props {
    width: string,
    userType: string,
    itemList: any,
}

function SideBar(props: Props) {

    const width = props.width;
    //passing list of items that will be displayed in the sidebar and their links(routing)
    const userType = props.userType;
    const itemList = props.itemList;
    return (

        <Box width={width} padding="20px" className={styles.container}>
            <Box className={styles.logo}>
                <Avatar className={styles.avatar} sx={{bgcolor: deepOrange[500]}}>
                    M
                </Avatar>
            </Box>
            {itemList.map((item: any) => (
                <Box>
                    <Link className={styles.link} href={"/" + userType + "/" + item.text}>
                        {item.text}
                    </Link>
                </Box>
            ))}
        </Box>

    );
}

export default SideBar;

