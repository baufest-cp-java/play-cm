package models

trait Entity[T] {
  def insert(entity: T)
  def update(entity: T)
  def delete(entity: T)
  def delete(id: Long)
  def persit(entity: T)
  def find(id: Long): Option[T]
  def list: List[T]
}
