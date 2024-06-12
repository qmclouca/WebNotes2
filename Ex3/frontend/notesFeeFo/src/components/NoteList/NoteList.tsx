
import React from 'react';
import { Note } from '../../services/api';
interface NoteListProps {
  notes: Note[];
}

const NoteList: React.FC<NoteListProps> = ({ notes }) => {
  return (
    <div>
      <h3>Notes</h3>
      <ul>
        {notes.map((note) => (
          <li key={note.id}>
            <h4>{note.title}</h4>
            <p>{note.content}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default NoteList;