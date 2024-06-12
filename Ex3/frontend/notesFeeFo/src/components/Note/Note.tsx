import React, { useEffect, useState } from 'react';
import { addNote, getNotes, Note } from '../../services/api';
import NoteList from '../NoteList/NoteList';


const Notes: React.FC = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [notes, setNotes] = useState<Note[]>([]);
  
    useEffect(() => {
      const fetchNotes = async () => {
        try {
          const clientId = localStorage.getItem('clientId');
          if (clientId) {
            console.log('Client ID found:', clientId);
            const fetchedNotes = await getNotes(parseInt(clientId));
            console.log('Fetched notes:', fetchedNotes);
            setNotes(fetchedNotes);
          } else {
            console.error('Client ID is not found in localStorage.');
          }
        } catch (error) {
          console.error('Error fetching notes:', error);
        }
      };
      fetchNotes();
    }, []);
  
    const handleAddNote = async () => {
      try {
        const clientId = Number(localStorage.getItem('clientId'));
        const newNote: Note = { id: 0, title, content, clientId };
        const addedNote = await addNote(newNote);
        console.log('Added note:', addedNote);
        setNotes([...notes, addedNote]);
        setTitle('');
        setContent('');
      } catch (error) {
        console.error('Error adding note:', error);
      }
    };
  
    return (
      <div>
        <h2>Your Notes</h2>
        <NoteList notes={notes} />
        <div>
          <h2>Add a new note</h2>
          <input type="text" placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} />
          <textarea placeholder="Content" value={content} onChange={(e) => setContent(e.target.value)}></textarea>
          <button onClick={handleAddNote}>Add Note</button>
        </div>
      </div>
    );
  };
  
  export default Notes;