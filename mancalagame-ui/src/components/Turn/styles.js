import { createUseStyles } from 'react-jss'
import { COLORS } from 'constants.js'

const useStyles = createUseStyles({
  turnWrapper: {
    marginTop: 50,
    paddingTop: 30,
    borderTop: '1px solid #f4f4f4',
    fontWeight: 'bold',
    fontSize: 42,
  },
  playerName: ({ playerId }) => ({
    color: COLORS[playerId],
  }),
})

export default useStyles
