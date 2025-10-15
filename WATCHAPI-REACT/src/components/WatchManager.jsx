import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';
import config from './config.js';

const WatchManager = () => {
  const [watches, setWatches] = useState([]);
  const [watch, setWatch] = useState({
    id: '',
    brand: '',
    model: '',
    price: '',
    color: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedWatch, setFetchedWatch] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  const baseUrl = `${config.url}/watchapi`;

  useEffect(() => {
    fetchAllWatches();
  }, []);

  const fetchAllWatches = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setWatches(res.data);
    } catch (error) {
      setMessage('Failed to fetch watches.');
    }
  };

  const handleChange = (e) => {
    setWatch({ ...watch, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    for (let key in watch) {
      if (!watch[key] || watch[key].toString().trim() === '') {
        setMessage(`Please fill out the ${key} field.`);
        return false;
      }
    }
    if (isNaN(watch.price) || Number(watch.price) <= 0) {
      setMessage('Price must be a valid positive number.');
      return false;
    }
    return true;
  };

  const addWatch = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, watch);
      setMessage('Watch added successfully.');
      fetchAllWatches();
      resetForm();
    } catch (error) {
      setMessage('Error adding watch.');
    }
  };

  const updateWatch = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, watch);
      setMessage('Watch updated successfully.');
      fetchAllWatches();
      resetForm();
    } catch (error) {
      setMessage('Error updating watch.');
    }
  };

  const deleteWatch = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllWatches();
    } catch (error) {
      setMessage('Error deleting watch.');
    }
  };

  const getWatchById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedWatch(res.data);
      setMessage('');
    } catch (error) {
      setFetchedWatch(null);
      setMessage('Watch not found.');
    }
  };

  const handleEdit = (w) => {
    setWatch(w);
    setEditMode(true);
    setMessage(`Editing watch with ID ${w.id}`);
  };

  const resetForm = () => {
    setWatch({
      id: '',
      brand: '',
      model: '',
      price: '',
      color: ''
    });
    setEditMode(false);
  };

  return (
    <div className="student-container">
      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}

      <h2>Watch Management</h2>

      <div>
        <h3>{editMode ? 'Edit Watch' : 'Add Watch'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="ID" value={watch.id} onChange={handleChange} />
          <input type="text" name="brand" placeholder="Brand" value={watch.brand} onChange={handleChange} />
          <input type="text" name="model" placeholder="Model" value={watch.model} onChange={handleChange} />
          <input type="number" name="price" placeholder="Price" value={watch.price} onChange={handleChange} />
          <input type="text" name="color" placeholder="Color" value={watch.color} onChange={handleChange} />
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addWatch}>Add Watch</button>
          ) : (
            <>
              <button className="btn-green" onClick={updateWatch}>Update Watch</button>
              <button className="btn-gray" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      <div>
        <h3>Get Watch By ID</h3>
        <input
          type="number"
          value={idToFetch}
          onChange={(e) => setIdToFetch(e.target.value)}
          placeholder="Enter ID"
        />
        <button className="btn-blue" onClick={getWatchById}>Fetch</button>

        {fetchedWatch && (
          <div>
            <h4>Watch Found:</h4>
            <pre>{JSON.stringify(fetchedWatch, null, 2)}</pre>
          </div>
        )}
      </div>

      <div>
        <h3>All Watches</h3>
        {watches.length === 0 ? (
          <p>No watches found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {Object.keys(watch).map((key) => (
                    <th key={key}>{key}</th>
                  ))}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {watches.map((w) => (
                  <tr key={w.id}>
                    {Object.keys(watch).map((key) => (
                      <td key={key}>{w[key]}</td>
                    ))}
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => handleEdit(w)}>Edit</button>
                        <button className="btn-red" onClick={() => deleteWatch(w.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default WatchManager;
