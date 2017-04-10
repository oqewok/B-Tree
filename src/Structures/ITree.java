package Structures;

public interface ITree<T> {

    /**
     * Добавляет значение в дерево. Дерево может содежать несколько одинаковых значений (values).
     *
     * @param value добавляется в дерево.
     * @return True если добавлено успешно.
     */
    public boolean add(T value);

    /**
     * Удаляет первое встретившееся значение из дерева.
     *
     * @param value удаляется из дерева.
     * @return T значение, удаляемое из дерева.
     */
    public T remove(T value);

    /**
     * Удаляет целое дерево.
     */
    public void clear();

    /**
     * Содержится ли такое значение в дереве.
     *
     * @param value определяемое в дереве.
     * @return True если дерево содержит значение.
     */
    public boolean contains(T value);

    /**
     * Возвращает количество узлов в дереве.
     *
     * @return Количество узлов в дереве.
     */
    public int size();

    /**
     * Возвращает порядок дерева.
     *
     * @return Порядок дерева.
     */
    public int order();

    /**
     * Проверяет дерево на соответствие инвариантности.
     * Validate the tree according to the invariants.
     * True if the tree is valid
     *
     * @return True если дерево правильно.
     */
    public boolean validate();

    /**
     * Берет дерево и переводит его в совместимое с Collection представление
     *
     * @return данные из дерева в виде, совместимом с java.util.Collection
     */
    public java.util.Collection<T> toCollection();

}
