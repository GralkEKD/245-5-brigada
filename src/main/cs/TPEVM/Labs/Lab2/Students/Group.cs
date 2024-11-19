using System.Text;

namespace Students
{
    public class Group
    {
        public string Number {get; set;} = string.Empty;
        private readonly Dictionary<uint, Student> _students = [];

        public Group() {}

        public Group(string number)
        {
            Number = number;
        }

        internal Group(string number, Student[] students)
        {
            Number = number;
            foreach (var student in students)
            {
                _students.Add(student.Id, student);
            }
        }

        public bool Add(Student student)
        {
            return _students.TryAdd(student.Id, student);
        }

        public bool Remove(uint id)
        {
            return _students.Remove(id);
        }

        public Student this[uint id] => _students[id];

        public string GetInfo()
        {
            StringBuilder stringBuilder = new();
            List<Student> studentValues = [.. _students.Values];
            studentValues.Sort(
                (x1, x2) => string.Compare(x1.LastName, x2.LastName, StringComparison.Ordinal)
            );
            foreach (var student in studentValues)
            {
                stringBuilder.Append('\t');
                stringBuilder.Append(student);
                stringBuilder.Append('\n');
            }
            return stringBuilder.ToString();
        }
    }
}