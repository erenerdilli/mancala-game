import { createUseStyles } from 'react-jss'

const useStyles = createUseStyles({
  pitArea: ({ id }) => ({
    display: 'flex',
    justifyContent: 'space-between',
    flexDirection: id % 2 === 0 && 'row-reverse',
  }),
})

export default useStyles
