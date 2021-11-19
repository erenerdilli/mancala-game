import { createUseStyles } from 'react-jss'

const PIT_WIDTH = 100
const PIT_HEIGHT = 170

const useStyles = createUseStyles({
  root: ({ isPool }) => ({
    padding: 10,
    border: '2px solid #444',
    borderRadius: 10,
    width: isPool ? PIT_WIDTH * 1.5 : PIT_WIDTH,
    minHeight: PIT_HEIGHT,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    flexWrap: 'wrap',
    backgroundColor: isPool && '#e6e5d5',
    cursor: 'pointer',
  })
})

export default useStyles
