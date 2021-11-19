import { createUseStyles } from 'react-jss'

const STONE_SIZE = 25

const useStyles = createUseStyles({
  root: {
    border: '2px solid #444',
    borderRadius: '100%',
    backgroundColor: 'orange',
    width: STONE_SIZE,
    height: STONE_SIZE,
  }
})

export default useStyles
