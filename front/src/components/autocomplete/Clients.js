import { fetchClientList } from '@/store/slices/clientSlice'
import { Autocomplete, TextField } from '@mui/material'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

const Clients = ({ value, onChange, picManager, sx, readOnly = false }) => {
  const dispatch = useDispatch()
  const { options, status, error } = useSelector((state) => state.clientList)

  useEffect(() => {
    dispatch(fetchClientList(picManager))
  }, [dispatch, picManager])

  const handleClientChange = (event, value) => {
    onChange(value ? value.clientId : '')
  }

  return (
    <Autocomplete
      value={
        options
          ? options.find((option) => option.clientId === value) || null
          : null
      }
      options={options || []}
      getOptionLabel={(options) => options.clientName || ''}
      onChange={handleClientChange}
      renderInput={(params) => (
        <TextField
          {...params}
          label="고객"
          InputProps={{
            ...params.InputProps,
            readOnly: readOnly,
          }}
        />
      )}
      disabled={readOnly}
      sx={sx}
    />
  )
}

Clients.displayName = 'Clients'

export default Clients
